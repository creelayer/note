import {createWebHistory, createRouter} from "vue-router";
import WorkTable from "@/components/WorkTable";

const routes = [
    {
        path: "/",
        name: "WorkTable",
        component: WorkTable,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;