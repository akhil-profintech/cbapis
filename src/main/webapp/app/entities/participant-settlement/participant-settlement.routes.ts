import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ParticipantSettlementComponent } from './list/participant-settlement.component';
import { ParticipantSettlementDetailComponent } from './detail/participant-settlement-detail.component';
import { ParticipantSettlementUpdateComponent } from './update/participant-settlement-update.component';
import ParticipantSettlementResolve from './route/participant-settlement-routing-resolve.service';

const participantSettlementRoute: Routes = [
  {
    path: '',
    component: ParticipantSettlementComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParticipantSettlementDetailComponent,
    resolve: {
      participantSettlement: ParticipantSettlementResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParticipantSettlementUpdateComponent,
    resolve: {
      participantSettlement: ParticipantSettlementResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParticipantSettlementUpdateComponent,
    resolve: {
      participantSettlement: ParticipantSettlementResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default participantSettlementRoute;
