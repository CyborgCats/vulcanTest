declare module '*.vue' {
    import { DefineComponent } from 'vue';
    const component: DefineComponent<{}, {}, any>;
    export default component;
    import { Router } from 'vue-router';
    const router: Router;
    export default router;
  }
  