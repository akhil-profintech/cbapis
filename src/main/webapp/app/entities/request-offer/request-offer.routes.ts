import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { RequestOfferComponent } from './list/request-offer.component';
import { RequestOfferDetailComponent } from './detail/request-offer-detail.component';
import { RequestOfferUpdateComponent } from './update/request-offer-update.component';
import RequestOfferResolve from './route/request-offer-routing-resolve.service';

const requestOfferRoute: Routes = [
  {
    path: '',
    component: RequestOfferComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RequestOfferDetailComponent,
    resolve: {
      requestOffer: RequestOfferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RequestOfferUpdateComponent,
    resolve: {
      requestOffer: RequestOfferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RequestOfferUpdateComponent,
    resolve: {
      requestOffer: RequestOfferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default requestOfferRoute;
