import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { SettlementComponent } from './list/settlement.component';
import { SettlementDetailComponent } from './detail/settlement-detail.component';
import { SettlementUpdateComponent } from './update/settlement-update.component';
import SettlementResolve from './route/settlement-routing-resolve.service';

const settlementRoute: Routes = [
  {
    path: '',
    component: SettlementComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SettlementDetailComponent,
    resolve: {
      settlement: SettlementResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SettlementUpdateComponent,
    resolve: {
      settlement: SettlementResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SettlementUpdateComponent,
    resolve: {
      settlement: SettlementResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default settlementRoute;
