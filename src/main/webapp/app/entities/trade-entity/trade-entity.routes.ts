import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { TradeEntityComponent } from './list/trade-entity.component';
import { TradeEntityDetailComponent } from './detail/trade-entity-detail.component';
import { TradeEntityUpdateComponent } from './update/trade-entity-update.component';
import TradeEntityResolve from './route/trade-entity-routing-resolve.service';

const tradeEntityRoute: Routes = [
  {
    path: '',
    component: TradeEntityComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TradeEntityDetailComponent,
    resolve: {
      tradeEntity: TradeEntityResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TradeEntityUpdateComponent,
    resolve: {
      tradeEntity: TradeEntityResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TradeEntityUpdateComponent,
    resolve: {
      tradeEntity: TradeEntityResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default tradeEntityRoute;
