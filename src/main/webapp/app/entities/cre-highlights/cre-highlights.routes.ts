import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CREHighlightsComponent } from './list/cre-highlights.component';
import { CREHighlightsDetailComponent } from './detail/cre-highlights-detail.component';
import { CREHighlightsUpdateComponent } from './update/cre-highlights-update.component';
import CREHighlightsResolve from './route/cre-highlights-routing-resolve.service';

const cREHighlightsRoute: Routes = [
  {
    path: '',
    component: CREHighlightsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CREHighlightsDetailComponent,
    resolve: {
      cREHighlights: CREHighlightsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CREHighlightsUpdateComponent,
    resolve: {
      cREHighlights: CREHighlightsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CREHighlightsUpdateComponent,
    resolve: {
      cREHighlights: CREHighlightsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default cREHighlightsRoute;
