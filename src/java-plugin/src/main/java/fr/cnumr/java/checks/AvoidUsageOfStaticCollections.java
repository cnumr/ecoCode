package fr.cnumr.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

@Rule(
        key = "S76",
        name = "Developpement",
        description = AvoidUsageOfStaticCollections.MESSAGE_RULE,
        priority = Priority.MINOR,
        tags = {"bug"})
public class AvoidUsageOfStaticCollections extends IssuableSubscriptionVisitor {

    protected static final String MESSAGE_RULE = "Avoid usage of static collections.";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(
                Tree.Kind.VARIABLE
        );
    }

    @Override
    public void visitNode(@Nonnull Tree tree) {
        if (tree instanceof VariableTree) {
            final VariableTree variableTree = (VariableTree) tree;

            if (variableTree.symbol().isStatic()) {
                reportIssue(variableTree, MESSAGE_RULE);
            }
        }
    }

}
