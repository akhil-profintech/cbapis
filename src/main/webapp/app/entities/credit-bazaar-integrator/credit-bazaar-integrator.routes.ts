import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CreditBazaarIntegratorComponent } from './list/credit-bazaar-integrator.component';
import { CreditBazaarIntegratorDetailComponent } from './detail/credit-bazaar-integrator-detail.component';
import { CreditBazaarIntegratorUpdateComponent } from './update/credit-bazaar-integrator-update.component';
import CreditBazaarIntegratorResolve from './route/credit-bazaar-integrator-routing-resolve.service';

const creditBazaarIntegratorRoute: Routes = [
  {
    path: '',
    component: CreditBazaarIntegratorComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreditBazaarIntegratorDetailComponent,
    resolve: {
      creditBazaarIntegrator: CreditBazaarIntegratorResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreditBazaarIntegratorUpdateComponent,
    resolve: {
      creditBazaarIntegrator: CreditBazaarIntegratorResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreditBazaarIntegratorUpdateComponent,
    resolve: {
      creditBazaarIntegrator: CreditBazaarIntegratorResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default creditBazaarIntegratorRoute;
