import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { IndividualAssessmentComponent } from './list/individual-assessment.component';
import { IndividualAssessmentDetailComponent } from './detail/individual-assessment-detail.component';
import { IndividualAssessmentUpdateComponent } from './update/individual-assessment-update.component';
import IndividualAssessmentResolve from './route/individual-assessment-routing-resolve.service';

const individualAssessmentRoute: Routes = [
  {
    path: '',
    component: IndividualAssessmentComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: IndividualAssessmentDetailComponent,
    resolve: {
      individualAssessment: IndividualAssessmentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: IndividualAssessmentUpdateComponent,
    resolve: {
      individualAssessment: IndividualAssessmentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: IndividualAssessmentUpdateComponent,
    resolve: {
      individualAssessment: IndividualAssessmentResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default individualAssessmentRoute;
