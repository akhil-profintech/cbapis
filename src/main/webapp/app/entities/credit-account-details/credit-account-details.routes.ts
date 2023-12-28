import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CreditAccountDetailsComponent } from './list/credit-account-details.component';
import { CreditAccountDetailsDetailComponent } from './detail/credit-account-details-detail.component';
import { CreditAccountDetailsUpdateComponent } from './update/credit-account-details-update.component';
import CreditAccountDetailsResolve from './route/credit-account-details-routing-resolve.service';

const creditAccountDetailsRoute: Routes = [
  {
    path: '',
    component: CreditAccountDetailsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreditAccountDetailsDetailComponent,
    resolve: {
      creditAccountDetails: CreditAccountDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreditAccountDetailsUpdateComponent,
    resolve: {
      creditAccountDetails: CreditAccountDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreditAccountDetailsUpdateComponent,
    resolve: {
      creditAccountDetails: CreditAccountDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default creditAccountDetailsRoute;
