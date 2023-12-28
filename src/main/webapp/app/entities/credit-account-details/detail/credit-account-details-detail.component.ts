import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ICreditAccountDetails } from '../credit-account-details.model';

@Component({
  standalone: true,
  selector: 'jhi-credit-account-details-detail',
  templateUrl: './credit-account-details-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CreditAccountDetailsDetailComponent {
  @Input() creditAccountDetails: ICreditAccountDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
