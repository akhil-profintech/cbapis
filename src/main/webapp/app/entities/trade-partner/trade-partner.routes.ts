import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { TradePartnerComponent } from './list/trade-partner.component';
import { TradePartnerDetailComponent } from './detail/trade-partner-detail.component';
import { TradePartnerUpdateComponent } from './update/trade-partner-update.component';
import TradePartnerResolve from './route/trade-partner-routing-resolve.service';

const tradePartnerRoute: Routes = [
  {
    path: '',
    component: TradePartnerComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TradePartnerDetailComponent,
    resolve: {
      tradePartner: TradePartnerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TradePartnerUpdateComponent,
    resolve: {
      tradePartner: TradePartnerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TradePartnerUpdateComponent,
    resolve: {
      tradePartner: TradePartnerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default tradePartnerRoute;
