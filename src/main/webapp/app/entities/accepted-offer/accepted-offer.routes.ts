import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { AcceptedOfferComponent } from './list/accepted-offer.component';
import { AcceptedOfferDetailComponent } from './detail/accepted-offer-detail.component';
import { AcceptedOfferUpdateComponent } from './update/accepted-offer-update.component';
import AcceptedOfferResolve from './route/accepted-offer-routing-resolve.service';

const acceptedOfferRoute: Routes = [
  {
    path: '',
    component: AcceptedOfferComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AcceptedOfferDetailComponent,
    resolve: {
      acceptedOffer: AcceptedOfferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AcceptedOfferUpdateComponent,
    resolve: {
      acceptedOffer: AcceptedOfferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AcceptedOfferUpdateComponent,
    resolve: {
      acceptedOffer: AcceptedOfferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default acceptedOfferRoute;
