package io.ecocode.java.checks.helpers;

import com.google.common.collect.ImmutableList;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;

import java.util.List;

public class ArgumentComplexTypeSubscriptionVisitor extends IssuableSubscriptionVisitor {

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD_INVOCATION);
    }

    /**
     * Method that gives the argument child value when it's of a complex type
     *
     * @param argument the argument with a complex type
     * @return the child expression of the argument that matched (for example if the argument is being cast)
     */
    public Object checkArgumentComplexType(ExpressionTree argument) {
        switch (argument.kind()) {
            case MEMBER_SELECT:
                MemberSelectExpressionTree memberSelectExpressionTree = (MemberSelectExpressionTree) argument;
                return (memberSelectExpressionTree.identifier());
            case TYPE_CAST:
                TypeCastTree typeCastTree = (TypeCastTree) argument;
                return (typeCastTree.expression());
            case PARENTHESIZED_EXPRESSION:
                ParenthesizedTree parenthesizedTree = (ParenthesizedTree) argument;
                return (parenthesizedTree.expression());
            default:
                return argument;
        }
    }
}