import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { FinanceRequestMappingComponent } from './list/finance-request-mapping.component';
import { FinanceRequestMappingDetailComponent } from './detail/finance-request-mapping-detail.component';
import { FinanceRequestMappingUpdateComponent } from './update/finance-request-mapping-update.component';
import FinanceRequestMappingResolve from './route/finance-request-mapping-routing-resolve.service';

const financeRequestMappingRoute: Routes = [
  {
    path: '',
    component: FinanceRequestMappingComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FinanceRequestMappingDetailComponent,
    resolve: {
      financeRequestMapping: FinanceRequestMappingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FinanceRequestMappingUpdateComponent,
    resolve: {
      financeRequestMapping: FinanceRequestMappingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FinanceRequestMappingUpdateComponent,
    resolve: {
      financeRequestMapping: FinanceRequestMappingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default financeRequestMappingRoute;
