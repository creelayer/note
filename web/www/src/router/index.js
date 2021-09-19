import {createWebHistory, createRouter} from "vue-router";
import WorkTable from "@/components/workTabke/WorkTable";
import Tags from "@/components/tags/Tags";
import Login from "@/components/auth/Login";
import Register from "@/components/auth/Register";

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
    },
    {
        path: "/login",
        name: "Login",
        component: Login
    },
    {
        path: "/create-account",
        name: "Register",
        component: Register
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;