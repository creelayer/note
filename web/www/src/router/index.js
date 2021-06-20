import {createWebHistory, createRouter} from "vue-router";
import WorkTable from "@/components/workTabke/WorkTable";
import Tags from "@/components/tags/Tags";

const routes = [
    {
        path: "/",
        name: "WorkTable",
        component:  WorkTable
    },
    {
        path: "/tags",
        name: "Tags",
        component: Tags
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;