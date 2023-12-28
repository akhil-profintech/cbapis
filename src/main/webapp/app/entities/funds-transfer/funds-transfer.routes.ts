import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { FundsTransferComponent } from './list/funds-transfer.component';
import { FundsTransferDetailComponent } from './detail/funds-transfer-detail.component';
import { FundsTransferUpdateComponent } from './update/funds-transfer-update.component';
import FundsTransferResolve from './route/funds-transfer-routing-resolve.service';

const fundsTransferRoute: Routes = [
  {
    path: '',
    component: FundsTransferComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FundsTransferDetailComponent,
    resolve: {
      fundsTransfer: FundsTransferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FundsTransferUpdateComponent,
    resolve: {
      fundsTransfer: FundsTransferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FundsTransferUpdateComponent,
    resolve: {
      fundsTransfer: FundsTransferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default fundsTransferRoute;
