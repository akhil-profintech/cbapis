import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CollectionTransactionDetailsComponent } from './list/collection-transaction-details.component';
import { CollectionTransactionDetailsDetailComponent } from './detail/collection-transaction-details-detail.component';
import { CollectionTransactionDetailsUpdateComponent } from './update/collection-transaction-details-update.component';
import CollectionTransactionDetailsResolve from './route/collection-transaction-details-routing-resolve.service';

const collectionTransactionDetailsRoute: Routes = [
  {
    path: '',
    component: CollectionTransactionDetailsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CollectionTransactionDetailsDetailComponent,
    resolve: {
      collectionTransactionDetails: CollectionTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CollectionTransactionDetailsUpdateComponent,
    resolve: {
      collectionTransactionDetails: CollectionTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CollectionTransactionDetailsUpdateComponent,
    resolve: {
      collectionTransactionDetails: CollectionTransactionDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default collectionTransactionDetailsRoute;
