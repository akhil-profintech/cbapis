import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { BeneValidationComponent } from './list/bene-validation.component';
import { BeneValidationDetailComponent } from './detail/bene-validation-detail.component';
import { BeneValidationUpdateComponent } from './update/bene-validation-update.component';
import BeneValidationResolve from './route/bene-validation-routing-resolve.service';

const beneValidationRoute: Routes = [
  {
    path: '',
    component: BeneValidationComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BeneValidationDetailComponent,
    resolve: {
      beneValidation: BeneValidationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BeneValidationUpdateComponent,
    resolve: {
      beneValidation: BeneValidationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BeneValidationUpdateComponent,
    resolve: {
      beneValidation: BeneValidationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default beneValidationRoute;
