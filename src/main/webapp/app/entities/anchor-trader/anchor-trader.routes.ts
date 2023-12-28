import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AnchorTraderComponent } from './list/anchor-trader.component';
import { AnchorTraderDetailComponent } from './detail/anchor-trader-detail.component';
import { AnchorTraderUpdateComponent } from './update/anchor-trader-update.component';
import AnchorTraderResolve from './route/anchor-trader-routing-resolve.service';

const anchorTraderRoute: Routes = [
  {
    path: '',
    component: AnchorTraderComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnchorTraderDetailComponent,
    resolve: {
      anchorTrader: AnchorTraderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AnchorTraderUpdateComponent,
    resolve: {
      anchorTrader: AnchorTraderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AnchorTraderUpdateComponent,
    resolve: {
      anchorTrader: AnchorTraderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default anchorTraderRoute;
