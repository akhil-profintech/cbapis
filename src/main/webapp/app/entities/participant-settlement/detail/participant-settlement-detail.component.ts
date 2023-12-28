import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IParticipantSettlement } from '../participant-settlement.model';

@Component({
  standalone: true,
  selector: 'jhi-participant-settlement-detail',
  templateUrl: './participant-settlement-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ParticipantSettlementDetailComponent {
  @Input() participantSettlement: IParticipantSettlement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
