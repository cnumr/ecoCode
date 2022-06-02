package fr.cnumr.python.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.SubscriptionContext;
import org.sonar.plugins.python.api.tree.CallExpression;
import org.sonar.plugins.python.api.tree.StringElement;
import org.sonar.plugins.python.api.tree.StringLiteral;
import org.sonar.plugins.python.api.tree.Tree;
import java.util.*;


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
		context.registerSyntaxNodeConsumer(Tree.Kind.FOR_STMT, this::visitNodeString);
	}

	public void visitNodeString(SubscriptionContext ctx) {
		StringLiteral stringLiteral = (StringLiteral) ctx.syntaxNode();

		for(StringElement stringElement :  stringLiteral.stringElements()) {
			if(checkIssue(stringElement, ctx)){
				CallExpression callExpression = (CallExpression) ctx.syntaxNode();
				if (callExpression.parent().getKind() == Tree.Kind.FOR_STMT ) {
					ctx.addIssue(callExpression, NoFunctionCallWhenDeclaringForLoop.DESCRIPTION);
				}
			}
		};
	}


	private void repport(StringElement stringElement, SubscriptionContext ctx) {
		if (stringElement.firstToken() != null) {
			final String classname = ctx.pythonFile().fileName();
			final int line = stringElement.firstToken().line();
			if (!linesWithIssuesByFile.containsKey(classname)) {
				linesWithIssuesByFile.put(classname, new ArrayList<>());
			}
			linesWithIssuesByFile.get(classname).add(line);
		}
		ctx.addIssue(stringElement, MESSAGERULE);
	}


	public boolean checkIssue(StringElement stringElement, SubscriptionContext ctx) {
		if (lineAlreadyHasThisIssue(stringElement, ctx)) return false;
		if (stringElement.value().contains(".execute(")) {
			repport(stringElement, ctx);
			return true;
		}
		return false;
	}


	private boolean lineAlreadyHasThisIssue(StringElement stringElement, SubscriptionContext ctx) {
		if (stringElement.firstToken() != null) {
			final String filename = ctx.pythonFile().fileName();
			final int line = stringElement.firstToken().line();

			return linesWithIssuesByFile.containsKey(filename)
					&& linesWithIssuesByFile.get(filename).contains(line);
		}
}