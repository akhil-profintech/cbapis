import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { RepaymentComponent } from './list/repayment.component';
import { RepaymentDetailComponent } from './detail/repayment-detail.component';
import { RepaymentUpdateComponent } from './update/repayment-update.component';
import RepaymentResolve from './route/repayment-routing-resolve.service';

const repaymentRoute: Routes = [
  {
    path: '',
    component: RepaymentComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RepaymentDetailComponent,
    resolve: {
      repayment: RepaymentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RepaymentUpdateComponent,
    resolve: {
      repayment: RepaymentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RepaymentUpdateComponent,
    resolve: {
      repayment: RepaymentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default repaymentRoute;
