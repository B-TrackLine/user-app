import { Routes } from '@angular/router';
import { UserAdministrationPageComponent } from './pages/useradministration/user-administration-page.component';

export const routes: Routes = [
    {
        path: '',
        component: UserAdministrationPageComponent,
        title: 'User Administration'
    },
];
