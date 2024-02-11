import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { TradeChannelComponent } from './list/trade-channel.component';
import { TradeChannelDetailComponent } from './detail/trade-channel-detail.component';
import { TradeChannelUpdateComponent } from './update/trade-channel-update.component';
import TradeChannelResolve from './route/trade-channel-routing-resolve.service';

const tradeChannelRoute: Routes = [
  {
    path: '',
    component: TradeChannelComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TradeChannelDetailComponent,
    resolve: {
      tradeChannel: TradeChannelResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TradeChannelUpdateComponent,
    resolve: {
      tradeChannel: TradeChannelResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TradeChannelUpdateComponent,
    resolve: {
      tradeChannel: TradeChannelResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default tradeChannelRoute;
