package masivian.model;

import java.util.ArrayList;

/**
 * Tree model for the assessment
 */
public class MasivianTree {
    public MasivianTree() {
    }

    private String id;
    private ArrayList <MasivianTreeNode> tree;

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<MasivianTreeNode> getTree() {
        return tree;
    }

    public void setTree(ArrayList<MasivianTreeNode> tree) {
        this.tree = tree;
    }
}
