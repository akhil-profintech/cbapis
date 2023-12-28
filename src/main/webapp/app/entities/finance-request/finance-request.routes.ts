import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { FinanceRequestComponent } from './list/finance-request.component';
import { FinanceRequestDetailComponent } from './detail/finance-request-detail.component';
import { FinanceRequestUpdateComponent } from './update/finance-request-update.component';
import FinanceRequestResolve from './route/finance-request-routing-resolve.service';

const financeRequestRoute: Routes = [
  {
    path: '',
    component: FinanceRequestComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FinanceRequestDetailComponent,
    resolve: {
      financeRequest: FinanceRequestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FinanceRequestUpdateComponent,
    resolve: {
      financeRequest: FinanceRequestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FinanceRequestUpdateComponent,
    resolve: {
      financeRequest: FinanceRequestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default financeRequestRoute;
