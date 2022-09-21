package fr.cnumr.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

@Rule(
        key = "S75",
        name = "Developpement",
        description = AvoidConcatenateStringsInLoop.MESSAGE_RULE,
        priority = Priority.MINOR,
        tags = {"bug"})
public class AvoidConcatenateStringsInLoop extends IssuableSubscriptionVisitor {

    public static final String MESSAGE_RULE = "Don't concatenate Strings in loop, use StringBuilder instead.";
    private static final String STRING_CLASS = String.class.getName();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Arrays.asList(
                Tree.Kind.FOR_STATEMENT,
                Tree.Kind.FOR_EACH_STATEMENT,
                Tree.Kind.WHILE_STATEMENT
        );
    }

    @Override
    public void visitNode(@Nonnull Tree tree) {
        if (tree instanceof ForStatementTree) {
            ForStatementTree forStatementTree = (ForStatementTree) tree;
            forStatementTree.statement().accept(new StringConcatenationVisitor());
        } else if (tree instanceof ForEachStatement) {
            ForEachStatement forEachStatement = (ForEachStatement) tree;
            forEachStatement.statement().accept(new StringConcatenationVisitor());
        } else if (tree instanceof WhileStatementTree) {
            WhileStatementTree whileStatementTree = (WhileStatementTree) tree;
            whileStatementTree.statement().accept(new StringConcatenationVisitor());
        }
    }

    private class StringConcatenationVisitor extends BaseTreeVisitor {
        @Override
        public void visitBinaryExpression(BinaryExpressionTree tree) {
            if (tree.is(Tree.Kind.PLUS)) {
                if (tree.leftOperand().symbolType().is(STRING_CLASS)) {
                    reportIssue(tree, MESSAGE_RULE);
                } else if (tree.rightOperand().symbolType().is(STRING_CLASS)) {
                    reportIssue(tree, MESSAGE_RULE);
                }
            } else if (tree.is(Tree.Kind.PLUS_ASSIGNMENT)) {
                if (tree.leftOperand().symbolType().is(STRING_CLASS)) {
                    reportIssue(tree, MESSAGE_RULE);
                }
            }
        }
    }
}
