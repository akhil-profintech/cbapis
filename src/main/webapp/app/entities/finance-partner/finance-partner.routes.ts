import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { FinancePartnerComponent } from './list/finance-partner.component';
import { FinancePartnerDetailComponent } from './detail/finance-partner-detail.component';
import { FinancePartnerUpdateComponent } from './update/finance-partner-update.component';
import FinancePartnerResolve from './route/finance-partner-routing-resolve.service';

const financePartnerRoute: Routes = [
  {
    path: '',
    component: FinancePartnerComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FinancePartnerDetailComponent,
    resolve: {
      financePartner: FinancePartnerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FinancePartnerUpdateComponent,
    resolve: {
      financePartner: FinancePartnerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FinancePartnerUpdateComponent,
    resolve: {
      financePartner: FinancePartnerResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default financePartnerRoute;
