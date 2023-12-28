import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { EscrowAccountDetailsComponent } from './list/escrow-account-details.component';
import { EscrowAccountDetailsDetailComponent } from './detail/escrow-account-details-detail.component';
import { EscrowAccountDetailsUpdateComponent } from './update/escrow-account-details-update.component';
import EscrowAccountDetailsResolve from './route/escrow-account-details-routing-resolve.service';

const escrowAccountDetailsRoute: Routes = [
  {
    path: '',
    component: EscrowAccountDetailsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EscrowAccountDetailsDetailComponent,
    resolve: {
      escrowAccountDetails: EscrowAccountDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EscrowAccountDetailsUpdateComponent,
    resolve: {
      escrowAccountDetails: EscrowAccountDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EscrowAccountDetailsUpdateComponent,
    resolve: {
      escrowAccountDetails: EscrowAccountDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default escrowAccountDetailsRoute;
