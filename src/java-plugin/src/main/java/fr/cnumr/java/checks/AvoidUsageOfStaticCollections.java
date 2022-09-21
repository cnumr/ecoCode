package fr.cnumr.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Tree;

import javax.annotation.Nonnull;
import java.util.List;

@Rule(
        key = "S76",
        name = "Developpement",
        description = AvoidUsageOfStaticCollections.MESSAGERULE,
        priority = Priority.MINOR,
        tags = {"bug"})
public class AvoidUsageOfStaticCollections extends IssuableSubscriptionVisitor {

    protected static final String  MESSAGERULE = "Avoid usage of static collections.";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return null;
    }

    @Override
    public void visitNode(@Nonnull Tree tree) {
        reportIssue(tree, MESSAGERULE);
    }

}
