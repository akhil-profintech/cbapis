import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AnchorTraderPartnerComponent } from './list/anchor-trader-partner.component';
import { AnchorTraderPartnerDetailComponent } from './detail/anchor-trader-partner-detail.component';
import { AnchorTraderPartnerUpdateComponent } from './update/anchor-trader-partner-update.component';
import AnchorTraderPartnerResolve from './route/anchor-trader-partner-routing-resolve.service';

const anchorTraderPartnerRoute: Routes = [
  {
    path: '',
    component: AnchorTraderPartnerComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnchorTraderPartnerDetailComponent,
    resolve: {
      anchorTraderPartner: AnchorTraderPartnerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AnchorTraderPartnerUpdateComponent,
    resolve: {
      anchorTraderPartner: AnchorTraderPartnerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AnchorTraderPartnerUpdateComponent,
    resolve: {
      anchorTraderPartner: AnchorTraderPartnerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default anchorTraderPartnerRoute;
