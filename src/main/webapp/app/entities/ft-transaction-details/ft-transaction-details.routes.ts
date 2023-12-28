import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { FTTransactionDetailsComponent } from './list/ft-transaction-details.component';
import { FTTransactionDetailsDetailComponent } from './detail/ft-transaction-details-detail.component';
import { FTTransactionDetailsUpdateComponent } from './update/ft-transaction-details-update.component';
import FTTransactionDetailsResolve from './route/ft-transaction-details-routing-resolve.service';

const fTTransactionDetailsRoute: Routes = [
  {
    path: '',
    component: FTTransactionDetailsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FTTransactionDetailsDetailComponent,
    resolve: {
      fTTransactionDetails: FTTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FTTransactionDetailsUpdateComponent,
    resolve: {
      fTTransactionDetails: FTTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FTTransactionDetailsUpdateComponent,
    resolve: {
      fTTransactionDetails: FTTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default fTTransactionDetailsRoute;
