import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { EscrowTransactionDetailsComponent } from './list/escrow-transaction-details.component';
import { EscrowTransactionDetailsDetailComponent } from './detail/escrow-transaction-details-detail.component';
import { EscrowTransactionDetailsUpdateComponent } from './update/escrow-transaction-details-update.component';
import EscrowTransactionDetailsResolve from './route/escrow-transaction-details-routing-resolve.service';

const escrowTransactionDetailsRoute: Routes = [
  {
    path: '',
    component: EscrowTransactionDetailsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EscrowTransactionDetailsDetailComponent,
    resolve: {
      escrowTransactionDetails: EscrowTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EscrowTransactionDetailsUpdateComponent,
    resolve: {
      escrowTransactionDetails: EscrowTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EscrowTransactionDetailsUpdateComponent,
    resolve: {
      escrowTransactionDetails: EscrowTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default escrowTransactionDetailsRoute;
