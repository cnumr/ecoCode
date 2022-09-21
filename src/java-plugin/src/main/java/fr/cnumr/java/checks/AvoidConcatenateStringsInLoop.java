package fr.cnumr.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Tree;

import javax.annotation.Nonnull;
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
        return null;
    }

    @Override
    public void visitNode(@Nonnull Tree tree) {
        super.visitNode(tree);
    }
}
