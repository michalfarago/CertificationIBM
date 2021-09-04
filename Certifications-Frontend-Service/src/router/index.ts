import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import CertificationDetail from "@/views/CertificationDetail.vue";
import Profile from "@/views/Profile.vue";
import EditCertificateForm from "@/views/EditCertificateForm.vue";
import Voucher from "@/views/Voucher.vue";
import VoucherDetail from "@/views/VoucherDetail.vue";
import Register from "@/views/Register.vue";
import AllUsers from "../views/AllUsers.vue";
import CertificationsOverview from "@/views/CertificationsOverview.vue";
import SkillsOverview from "@/views/SkillsOverview.vue";
import EditSkillForm from "@/views/EditSkillForm.vue";
Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/certificationsoverview",
    name: "Certificationsoverview",
    component: CertificationsOverview
  },
  {
    path: "/skillsoverview",
    name: "SkillsOverview",
    component: SkillsOverview
  },
  {
    path: "/voucher",
    name: "Voucher",
    component: Voucher
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile
  },
  {
    path: "/certificationDetail/:id",
    name: "CertificationDetail",
    component: CertificationDetail
  },
  {
    path: "/edit-certificate-form/:id",
    name: "edit-certificate-form",
    component: EditCertificateForm
  },
  {
    path: "/edit-skill-form/:id",
    name: "edit-skill-form",
    component: EditSkillForm
  },
  {
    path: "/voucherDetail/:id",
    name: "VoucherDetail",
    component: VoucherDetail
  },
  {  
    path: "/register",
    name: "Register",
    component: Register
  },
  {  
    path: "/all-users",
    name: "Users",
    component: AllUsers
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
