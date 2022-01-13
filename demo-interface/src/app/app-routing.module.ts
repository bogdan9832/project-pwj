import { AdminComponent } from './views/admin/admin.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './views/customer/customer.component';

const routes: Routes = [
  {
    path:"admin",
    component: AdminComponent
  },
  {
    path:"",
    component: CustomerComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
