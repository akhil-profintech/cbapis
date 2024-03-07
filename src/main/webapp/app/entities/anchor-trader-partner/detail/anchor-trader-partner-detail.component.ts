import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAnchorTraderPartner } from '../anchor-trader-partner.model';

@Component({
  standalone: true,
  selector: 'jhi-anchor-trader-partner-detail',
  templateUrl: './anchor-trader-partner-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AnchorTraderPartnerDetailComponent {
  @Input() anchorTraderPartner: IAnchorTraderPartner | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
