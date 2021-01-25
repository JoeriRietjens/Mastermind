import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { store } from './store'
import VueNotifications from "vue-notifications";
import miniToastr from "mini-toastr";
const toastTypes = {
  success: "success",
  error: "error",
  info: "info",
  warn: "warn"
};

miniToastr.init({ types: toastTypes });
const toast = ({ title, message, type, timeout, cb }) => {
  return miniToastr[type](message, title, timeout, cb);
};

const options = {
  success: toast,
  error: toast,
  info: toast,
  warn: toast
};

Vue.use(VueNotifications, options);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
