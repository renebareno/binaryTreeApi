package masivian.model;

import java.util.Objects;

/**
 * Node model for the assessment
 */
public class MasivianTreeNode {

    private Integer value;
    private Integer leftNode;
    private Integer rightNode;


    public MasivianTreeNode(Integer value, Integer leftNode, Integer rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public MasivianTreeNode() {
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Integer leftNode) {
        this.leftNode = leftNode;
    }

    public Integer getRightNode() {
        return rightNode;
    }

    public void setRightNode(Integer rightNode) {
        this.rightNode = rightNode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MasivianTreeNode that = (MasivianTreeNode) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(leftNode, that.leftNode) &&
                Objects.equals(rightNode, that.rightNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, leftNode, rightNode);
    }
}
