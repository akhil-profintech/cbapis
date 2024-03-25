import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { FundsTransferTransactionDetailsComponent } from './list/funds-transfer-transaction-details.component';
import { FundsTransferTransactionDetailsDetailComponent } from './detail/funds-transfer-transaction-details-detail.component';
import { FundsTransferTransactionDetailsUpdateComponent } from './update/funds-transfer-transaction-details-update.component';
import FundsTransferTransactionDetailsResolve from './route/funds-transfer-transaction-details-routing-resolve.service';

const fundsTransferTransactionDetailsRoute: Routes = [
  {
    path: '',
    component: FundsTransferTransactionDetailsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FundsTransferTransactionDetailsDetailComponent,
    resolve: {
      fundsTransferTransactionDetails: FundsTransferTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FundsTransferTransactionDetailsUpdateComponent,
    resolve: {
      fundsTransferTransactionDetails: FundsTransferTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FundsTransferTransactionDetailsUpdateComponent,
    resolve: {
      fundsTransferTransactionDetails: FundsTransferTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default fundsTransferTransactionDetailsRoute;
