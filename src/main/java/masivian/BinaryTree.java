package masivian;

import masivian.model.BasicResponse;

import static spark.Spark.get;
import static spark.Spark.post;

public class BinaryTree {

    /**
     * Spark main
     *
     * @param args
     */
    public static void main(String[] args) {

        TreeController treeController = new TreeController();

        /**
         * Tree from the assessment example
         */
        get("/defaultTree", (req, res) -> {
            BasicResponse response = treeController.getDefaultTree();
            res.status(response.getStatus());
            res.type("application/json");
            return response.getMessage();
        });

        /**
         * lowestCommonAncestor given a treeId and value for two nodes
         */
        get("/lowestCommonAncestor", (req, res) -> {

            BasicResponse response = treeController.lowestCommonAncestor(req.queryParams("treeId"),
                    Integer.valueOf(req.queryParams("child")), Integer.valueOf(req.queryParams("otherChild")));
            res.status(response.getStatus());
            res.type("application/json");
            return response.getMessage();
        });

        /**
         * New tree creation
         */
        post("/newTree", (req, res) -> {
            BasicResponse response = treeController.createNewTree(req.body());
            res.status(response.getStatus());
            res.type("application/json");
            return response.getMessage();
        });

        /**
         * showTrees
         */
        get("/showTrees", (req, res) -> {
            BasicResponse response = treeController.showTrees();
            res.status(response.getStatus());
            res.type("application/json");
            return response.getMessage();
        });
    }
}
