package fr.cnumr.python.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.python.api.PythonFile;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.SubscriptionContext;
import org.sonar.plugins.python.api.tree.*;

import java.util.*;
import org.sonar.plugins.java.api.tree.ClassTree;


@Rule(
		key = "S64",
		name = "Developpement",
		description = AvoidSQLRequestInLoop.MESSAGERULE,
		priority = Priority.MINOR,
		tags = {"bug" })
public class AvoidSQLRequestInLoop extends PythonSubscriptionCheck {

	public static final String MESSAGERULE = "Avoid perform an SQL query inside a loop";
	private static final Map<String, Collection<Integer>> linesWithIssuesByFile = new HashMap<>();

	@Override
	public void initialize(Context context) {
		context.registerSyntaxNodeConsumer(Tree.Kind.FOR_STMT, ctx -> {
			ForStatement forStatement = (ForStatement) ctx.syntaxNode();
			StatementList list = (StatementList) forStatement.body();
			for(Statement a : list.statements()){
				if (a.getKind().equals(Tree.Kind.EXPRESSION_STMT)){
					ExpressionStatement expression = (ExpressionStatement) a;
					for(Expression i : expression.expressions()){
						CallExpression call = (CallExpression) i;
						for( Tree ele : call.callee().children()){
						if(ele.getKind().equals(Tree.Kind.NAME)){
						Name name = (Name)  ele;
						System.out.println(name.name());
						if (name.name().equals("execute")) {
							ctx.addIssue(call, MESSAGERULE);
						}
						}
					}
				}

			}}

		});
		context.registerSyntaxNodeConsumer(Tree.Kind.WHILE_STMT, ctx -> {
			WhileStatement forStatement = (WhileStatement) ctx.syntaxNode();
			StatementList list = (StatementList) forStatement.body();
			for(Statement a : list.statements()){
				if (a.getKind().equals(Tree.Kind.EXPRESSION_STMT)){
					ExpressionStatement expression = (ExpressionStatement) a;
					for(Expression i : expression.expressions()){
						CallExpression call = (CallExpression) i;
						Name name = (Name)  call.callee().children().get(0);
						if (name.name().equals("cursor")){
							ctx.addIssue(call, MESSAGERULE);

						}
					}
				}

			}

		});
	}

}