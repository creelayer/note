import {createApp} from 'vue'
import App from './App.vue'
import router from './router'

// export default {
//     directives: {
//         tooltip: {
//             bind: function (el) {
//                 el.addEventListener('mouseover', function (evt) {
//                     let targetEl = evt.target;
//                     if (targetEl.offsetWidth < targetEl.scrollWidth) {
//                         targetEl.setAttribute('title', evt.target.textContent);
//                     } else {
//                         targetEl.hasAttribute('title') && targetEl.removeAttribute('title');
//                     }
//                 });
//             }
//         }
//     }
// };


const app = createApp(App).use(router);
// app.config.globalProperties.$token = "zzz";

// app.directive('tooltip',  {
//     mounted(el, binding) {
//         init(el, binding);
//     },
//     updated(el, binding) {
//         init(el, binding);
//     }
// });
//
// function init(el, binding) {
//     let position = binding.arg || "top";
//     let tooltipText = binding.value || "Tooltip text";
//     el.setAttribute("position", position);
//     el.setAttribute("tooltip", tooltipText);
// }


app.mount('#app');

// app.directive("tooltip", function(el, binding){
//     $(el).tooltip({
//         title: binding.value,
//         placement: binding.arg,
//         trigger: 'hover'
//     })
// });


// var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
// var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
//     return new bootstrap.Tooltip(tooltipTriggerEl)
// })

