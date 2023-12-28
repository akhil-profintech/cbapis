import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { TradeComponent } from './list/trade.component';
import { TradeDetailComponent } from './detail/trade-detail.component';
import { TradeUpdateComponent } from './update/trade-update.component';
import TradeResolve from './route/trade-routing-resolve.service';

const tradeRoute: Routes = [
  {
    path: '',
    component: TradeComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TradeDetailComponent,
    resolve: {
      trade: TradeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TradeUpdateComponent,
    resolve: {
      trade: TradeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TradeUpdateComponent,
    resolve: {
      trade: TradeResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default tradeRoute;
