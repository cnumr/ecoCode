package fr.cnumr.python.checks;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.SubscriptionCheck;
import org.sonar.plugins.python.api.SubscriptionContext;
import org.sonar.plugins.python.api.symbols.FunctionSymbol;
import org.sonar.plugins.python.api.symbols.Symbol;
import org.sonar.plugins.python.api.tree.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Rule(
        key = AvoidGlobalVariableInFunctionCheck.RULE_KEY,
        name = "Developpement",
        description = AvoidGlobalVariableInFunctionCheck.DESCRIPTION,
        priority = Priority.MINOR,
        tags = {"bug"})
public class AvoidGlobalVariableInFunctionCheck extends PythonSubscriptionCheck {
    private static final Logger LOGGER = Loggers.get(AvoidGlobalVariableInFunctionCheck.class);

    public static final String RULE_KEY = "D4";
    public static final String DESCRIPTION = "Use local variable (function/class scope) instead of global variable (application scope)";

    private List<String> globalVariables = new ArrayList<>();
    private List<String> localVariablesDefinedInCurrentFunction;
    private Map<Tree, String> localVariablesUsedInCurrentFunction;

    @Override
    public void initialize(SubscriptionCheck.Context context) {
        context.registerSyntaxNodeConsumer(Tree.Kind.FILE_INPUT, this::visitFileInput);
        context.registerSyntaxNodeConsumer(Tree.Kind.FUNCDEF, this::visitFuncDef);
    }

    public void visitFileInput(SubscriptionContext ctx) {
        FileInput fileInput = (FileInput) ctx.syntaxNode();
        fileInput.globalVariables().stream().filter(v -> v.is(Symbol.Kind.OTHER)).forEach(v -> this.globalVariables.add(v.name()));
    }

    void visitFuncDef(SubscriptionContext ctx) {
        this.localVariablesDefinedInCurrentFunction = new ArrayList<>();
        this.localVariablesUsedInCurrentFunction = new HashMap<>();

        FunctionDef functionDef = (FunctionDef) ctx.syntaxNode();

        ParameterList parameterList = functionDef.parameters();
        if (parameterList != null) {
            parameterList.nonTuple().forEach(p -> this.localVariablesDefinedInCurrentFunction.add(extractVariableNameFromExpression(p.name())));
        }

        for (Statement statement : functionDef.body().statements()) {
            if (statement.is(Tree.Kind.ASSIGNMENT_STMT)) {
                visitAssignmentStatement((AssignmentStatement) statement);
            }
            if (statement.is(Tree.Kind.EXPRESSION_STMT)) {
                visitExpressionStatement((ExpressionStatement) statement);
            }
        }

        for (Map.Entry<Tree, String> entry : this.localVariablesUsedInCurrentFunction.entrySet()) {
            if (this.localVariablesDefinedInCurrentFunction.contains(entry.getValue())) {
                continue;
            }
            if (this.globalVariables.contains(entry.getValue())) {
                ctx.addIssue(entry.getKey(), DESCRIPTION);
            }
        }
    }

    void visitAssignmentStatement(AssignmentStatement s) {
        for (ExpressionList expressionList : s.lhsExpressions()) {
            for (Expression expression : expressionList.expressions()) {
                String variableName = extractVariableNameFromExpression(expression);
                if (variableName != null) {
                    this.localVariablesDefinedInCurrentFunction.add(variableName);
                }
            }
        }
    }

    void visitExpressionStatement(ExpressionStatement s) {
        for (Expression expression : s.expressions()) {
            String variableName = extractVariableNameFromExpression(expression);
            if (variableName != null) {
                this.localVariablesUsedInCurrentFunction.put(expression, variableName);
            }
        }
    }

    String extractVariableNameFromExpression(Expression expression) {
        if (expression.is(Tree.Kind.CALL_EXPR)) {
            ArgList argList = ((CallExpression) expression).argumentList();
            if (argList != null) {
                for (Argument argument : argList.arguments()) {
                    if (argument.is(Tree.Kind.REGULAR_ARGUMENT)) {
                        return extractVariableNameFromExpression(((RegularArgument) argument).expression());
                    }
                }
            }
        }

        if (expression.is(Tree.Kind.NAME)) {
            return ((Name) expression).name();
        }

        return null;
    }
}
