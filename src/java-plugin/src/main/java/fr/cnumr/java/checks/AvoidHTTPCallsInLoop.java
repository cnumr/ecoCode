package fr.cnumr.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Arrays;
import java.util.List;

@Rule(
        key = "S110",
        name = "Developpement",
        description = AvoidHTTPCallsInLoop.MESSAGERULE,
        priority = Priority.MINOR,
        tags = {"bug"}
)
public class AvoidHTTPCallsInLoop extends IssuableSubscriptionVisitor {

    protected static final String MESSAGERULE = "Avoid HTTP calls in loop";

    private final AvoidHTTPCallsInLoop.AvoidHTTPCallsInLoopVisitor visitorInFile = new AvoidHTTPCallsInLoop.AvoidHTTPCallsInLoopVisitor();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Arrays.asList(
                Tree.Kind.FOR_EACH_STATEMENT,
                Tree.Kind.FOR_STATEMENT,
                Tree.Kind.WHILE_STATEMENT,
                Tree.Kind.DO_STATEMENT
        );
    }

    @Override
    public void visitNode(Tree tree) {
        tree.accept(visitorInFile);
    }

    private class AvoidHTTPCallsInLoopVisitor extends BaseTreeVisitor {

        private static final String HTTP_CLIENT_CALL = "java.net.http.HttpClient";
        private static final String WEB_CLIENT_CALL = "org.springframework.web.reactive.function.client.WebClient";

        private static final String APACHE_CLIENT_CALL = "org.apache.http.client.HttpClient";

        private final MethodMatchers HTTP_METHOD = MethodMatchers.or(
                MethodMatchers
                        .create()
                        .ofTypes(HTTP_CLIENT_CALL)
                        .names("sendAsync", "send")
                        .withAnyParameters()
                        .build(),
                MethodMatchers
                        .create()
                        .ofSubTypes(WEB_CLIENT_CALL)
                        .names("get", "post", "put", "delete", "head")
                        .withAnyParameters()
                        .build(),
                MethodMatchers
                        .create()
                        .ofSubTypes(APACHE_CLIENT_CALL)
                        .names("execute")
                        .withAnyParameters()
                        .build()
        );

        @Override
        public void visitMethodInvocation(MethodInvocationTree tree) {
            if (HTTP_METHOD.matches(tree)) {
                reportIssue(tree, MESSAGERULE);
            } else {
                super.visitMethodInvocation(tree);
            }
        }
    }
}
