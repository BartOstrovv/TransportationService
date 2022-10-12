import {RouterModule, Routes} from "@angular/router";
import {MainLayoutComponent} from "@modules/main/layout/main-layout.component";
import {AdminGuard} from "@guards/admin.guard";
import {TransporterGuard} from "@guards/transporter.guard";
import {CustomerGuard} from "@guards/customer.guard";

const routes: Routes = [
  { path: "", component: MainLayoutComponent,
  children: [
    {path: "", loadChildren: ()=>import('./components/dashboard/dashboard.component')
        .then(m=>m.DashboardModule)},
    {path: "login", loadChildren: ()=>import('./components/login/login.component')
        .then(m=>m.LoginModule)},
    {path: "deliveries", loadChildren: ()=>import('./components/deliveries/deliveries.component')
        .then(m=>m.DeliveriesModule), canActivate: [TransporterGuard]},
    {path: "deliveries/my", loadChildren: ()=>import('./components/deliveries/deliveries.component')
        .then(m=>m.DeliveriesModule), canActivate: [CustomerGuard]},
    {path: "deliveries/create", loadChildren: ()=>import('./components/delivery/delivery.component')
        .then(m=>m.DeliveryModule), canActivate: [CustomerGuard]},
    {path: "deliveries/:id", loadChildren: ()=>import('./components/delivery/delivery.component')
        .then(m=>m.DeliveryModule), canActivate: [CustomerGuard]},
    {path: "deliveries/:id/offer", loadChildren: ()=>import('./components/offer/offer.component')
        .then(m=>m.OfferModule), canActivate: [TransporterGuard]},
    {path: "deliveries/:id/offers", loadChildren: ()=>import('./components/offers/offers.component')
        .then(m=>m.OffersModule), canActivate: [CustomerGuard]},
    {path: "transporters/:id/offers", loadChildren: ()=>import('./components/offers/offers.component')
        .then(m=>m.OffersModule), canActivate: [TransporterGuard]},
  ]}
]

export const routing = RouterModule.forChild(routes);
