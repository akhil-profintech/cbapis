import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IEscrowTransactionDetails } from '../escrow-transaction-details.model';

@Component({
  standalone: true,
  selector: 'jhi-escrow-transaction-details-detail',
  templateUrl: './escrow-transaction-details-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class EscrowTransactionDetailsDetailComponent {
  @Input() escrowTransactionDetails: IEscrowTransactionDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
