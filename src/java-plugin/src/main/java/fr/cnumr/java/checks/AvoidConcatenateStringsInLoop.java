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
        description = AvoidConcatenateStringsInLoop.MESSAGERULE,
        priority = Priority.MAJOR,
        tags = {"bug"})
public class AvoidConcatenateStringsInLoop extends IssuableSubscriptionVisitor {

    protected static final String MESSAGERULE = "Don't concatenate Strings, use StringBuilder instead.";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Arrays.asList(
                Tree.Kind.PLUS_ASSIGNMENT,
                Tree.Kind.PLUS
        );
    }

    @Override
    public void visitNode(@Nonnull Tree tree) {
        if (tree instanceof BinaryExpressionTree) {
            final BinaryExpressionTree binaryExpressionTree = (BinaryExpressionTree) tree;

            if (binaryExpressionTree.leftOperand().symbolType().is("java.lang.String") ||
                    binaryExpressionTree.rightOperand().symbolType().is("java.lang.String")) {
                reportIssue(tree, MESSAGERULE);
            }

        } else if (tree instanceof AssignmentExpressionTree) {
            final AssignmentExpressionTree assignmentExpressionTree = (AssignmentExpressionTree) tree;

            if (assignmentExpressionTree.variable().symbolType().is("java.lang.String")) {
                reportIssue(tree, MESSAGERULE);
            }

        }
    }
}
