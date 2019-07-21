package masivian.test;

import masivian.TreeController;
import masivian.model.BasicResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTreeController {

    private final String defaultTree ="{ \"id\": \"tree\", \"tree\": [ { \"value\": 67, \"leftNode\": 39, \"rightNode\": 76 }, { \"value\": 39, \"leftNode\": 28, \"rightNode\": 44 }, { \"value\": 28, \"rightNode\": 29 }, { \"value\": 29 }, { \"value\": 44 }, { \"value\": 76, \"leftNode\": 74, \"rightNode\": 85 }, { \"value\": 74 }, { \"value\": 85, \"leftNode\": 83, \"rightNode\": 87 }, { \"value\": 83 }, { \"value\": 87 } ] }";

    private final String newTreeCreated  ="\"new tree added to current session.\"";
    private final String nodeResponse="{\"value\":85,\"leftNode\":83,\"rightNode\":87}";

    @Test
    public void testCreateTree() {
        TreeController treeController = new TreeController();
        BasicResponse basicResponse = treeController.createNewTree(defaultTree);
        assertEquals(basicResponse.getMessage(),newTreeCreated);
    }

    @Test
    public void testLowestCommonAncestor() {
        TreeController treeController = new TreeController();
        BasicResponse basicResponse = treeController.createNewTree(defaultTree);
        assertEquals(basicResponse.getMessage(),newTreeCreated);

        basicResponse = treeController.lowestCommonAncestor("tree",83,87);
        assertEquals(basicResponse.getMessage(),nodeResponse);

    }

}
