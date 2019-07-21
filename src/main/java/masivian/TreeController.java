package masivian;


import com.google.gson.Gson;
import masivian.model.BasicResponse;
import masivian.model.MasivianTree;
import masivian.model.MasivianTreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TreeController {

    private ArrayList<MasivianTree> currentTrees;

    /**
     * Validates a tree
     * that the id is not repeated for other tree in session
     * validate unique values (in values)
     * Validate a root node in first position.
     *
     * @param stringTree
     * @return
     */
    public BasicResponse createNewTree(String stringTree) {

        String statusDescription = "new tree added to current session.";
        MasivianTree newTree = new Gson().fromJson(stringTree, MasivianTree.class);
        if (currentTrees == null) {
            currentTrees = new ArrayList<>();
        }

        if (currentTrees.stream().filter(tree -> tree.getId().equals(newTree.getId())).count() != 0) {
            statusDescription = "No tree added, id repeated";
        } else if (treeHasDuplicatesValues(newTree.getTree())) {
            statusDescription = "No tree added, there are some repeated node values";
        } else if (!treeHasRootNodeOnFirstPos(newTree.getTree())) {
            statusDescription = "No tree added, no root identified";
        } else {
            currentTrees.add(newTree);
        }

        return new BasicResponse(200, new Gson().toJson(statusDescription));
    }

    /**
     * given two nodes and a tree Id (previously saved) retrieves the  lowest common ancestor.
     * @param treeId
     * @param child
     * @param otherChild
     * @return
     */
    public BasicResponse lowestCommonAncestor(String treeId, Integer child, Integer otherChild) {

        BasicResponse basicResponse = new BasicResponse(200, "");
        MasivianTreeNode commonAncestor = new MasivianTreeNode();

        MasivianTree tree = currentTrees == null ? null : currentTrees.stream().filter(
                currentTree -> currentTree.getId().equals(treeId)).findAny().orElse(null);
        if (tree == null) {
            basicResponse.setMessage("No treeId match found.");
            basicResponse.setStatus(404);
        } else if (tree == null) {
            basicResponse.setMessage("No treeId match found.");
            basicResponse.setStatus(404);
        } else {
            ArrayList<MasivianTreeNode> childParents = getParents(tree, child);
            ArrayList<MasivianTreeNode> otherChildParents = getParents(tree, otherChild);
            for (MasivianTreeNode currentNode : tree.getTree()) {
                MasivianTreeNode childAncestor = childParents.stream().filter(ancestor ->
                        ancestor.getValue().equals(currentNode.getValue())).findAny().orElse(null);
                MasivianTreeNode otherChildAncestor = otherChildParents.stream().filter(ancestor ->
                        ancestor.getValue().equals(currentNode.getValue())).findAny().orElse(null);
                if (childAncestor != null && otherChildAncestor != null) {
                    commonAncestor = currentNode;
                }
            }
            basicResponse.setMessage(new Gson().toJson(commonAncestor));
        }

        return basicResponse;
    }

    /**
     * creates a tree as the assessment example
     *
     * @return a tree
     */
    public BasicResponse getDefaultTree() {

        ArrayList<MasivianTreeNode> tree = new ArrayList<>();
        tree.add(new MasivianTreeNode(67, 39, 76));
        tree.add(new MasivianTreeNode(39, 28, 44));
        tree.add(new MasivianTreeNode(28, null, 29));
        tree.add(new MasivianTreeNode(29, null, null));

        tree.add(new MasivianTreeNode(44, null, null));
        tree.add(new MasivianTreeNode(76, 74, 85));
        tree.add(new MasivianTreeNode(74, null, null));
        tree.add(new MasivianTreeNode(85, 83, 87));
        tree.add(new MasivianTreeNode(83, null, null));
        tree.add(new MasivianTreeNode(87, null, null));
        Gson gson = new Gson();

        MasivianTree msTree = new MasivianTree();
        msTree.setId("tree");
        msTree.setTree(tree);

        return new BasicResponse(200, gson.toJson(msTree));
    }


    private boolean treeHasDuplicatesValues(ArrayList<MasivianTreeNode> masivianTree) {

        Set<Integer> allINodes = new HashSet<>();
        masivianTree.stream().forEach(node -> allINodes.add(node.getValue()));
        return allINodes.size() != masivianTree.size();
    }

    private boolean treeHasRootNodeOnFirstPos(ArrayList<MasivianTreeNode> masivianTree) {

        MasivianTreeNode rootNode = masivianTree.get(0);
        return masivianTree.stream().filter(node -> rootNode.getValue().equals(node.getLeftNode()) ||
                rootNode.getValue().equals(node.getRightNode())).count() == 0;
    }

    private ArrayList<MasivianTreeNode> getParents(MasivianTree tree, Integer otherChild) {
        ArrayList<MasivianTreeNode> getParents = new ArrayList<>();
        MasivianTreeNode ancestor = new MasivianTreeNode();
        MasivianTreeNode root = tree.getTree().get(0);
        while (ancestor != null) {
            final Integer otherChildFinal = otherChild;
            if (root.getValue().equals(otherChild)) {
                ancestor = null;
            } else {
                Optional<MasivianTreeNode> optionalAncestor = tree.getTree().stream().filter(n ->
                        (n.getRightNode() != null && n.getRightNode().equals(otherChildFinal)) ||
                                (n.getLeftNode() != null && n.getLeftNode().equals(otherChildFinal))).findAny();
                ancestor = optionalAncestor.isPresent() ? optionalAncestor.get() : null;
                if (ancestor != null) {
                    getParents.add(ancestor);
                    otherChild = ancestor.getValue();
                }
            }
        }
        return getParents;
    }

    public BasicResponse showTrees() {
        return new BasicResponse(200, new Gson().toJson(currentTrees));
    }
}
