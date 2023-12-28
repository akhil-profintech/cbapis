import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { PlacedOfferComponent } from './list/placed-offer.component';
import { PlacedOfferDetailComponent } from './detail/placed-offer-detail.component';
import { PlacedOfferUpdateComponent } from './update/placed-offer-update.component';
import PlacedOfferResolve from './route/placed-offer-routing-resolve.service';

const placedOfferRoute: Routes = [
  {
    path: '',
    component: PlacedOfferComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PlacedOfferDetailComponent,
    resolve: {
      placedOffer: PlacedOfferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PlacedOfferUpdateComponent,
    resolve: {
      placedOffer: PlacedOfferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PlacedOfferUpdateComponent,
    resolve: {
      placedOffer: PlacedOfferResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default placedOfferRoute;
