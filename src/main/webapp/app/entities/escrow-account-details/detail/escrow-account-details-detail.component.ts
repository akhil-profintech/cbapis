import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IEscrowAccountDetails } from '../escrow-account-details.model';

@Component({
  standalone: true,
  selector: 'jhi-escrow-account-details-detail',
  templateUrl: './escrow-account-details-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class EscrowAccountDetailsDetailComponent {
  @Input() escrowAccountDetails: IEscrowAccountDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
